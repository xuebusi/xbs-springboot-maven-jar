from os import path

import imageio
import numpy as np

from py.gno.util.file_management import get_filenames

EPS = 1e-7


def generate_table_of_results(image_filenames, segmentation_folder, gt_folder, is_training=False):
    cup_dices = np.zeros(len(image_filenames), dtype=np.float)
    disc_dices = np.zeros(len(image_filenames), dtype=np.float)
    ae_cdrs = np.zeros(len(image_filenames), dtype=np.float)

    for i in range(len(image_filenames)):

        segmentation = imageio.imread(path.join(segmentation_folder, image_filenames[i]))
        if len(segmentation.shape) > 2:
            segmentation = segmentation[:, :, 0]
        if is_training:
            gt_filename = path.join(gt_folder, 'Glaucoma', image_filenames[i])
            if path.exists(gt_filename):
                gt_label = imageio.imread(gt_filename)
            else:
                gt_filename = path.join(gt_folder, 'Non-Glaucoma', image_filenames[i])
                if path.exists(gt_filename):
                    gt_label = imageio.imread(gt_filename)
                else:
                    raise ValueError('not find image_filenames '.format(image_filenames[i]))
        else:
            gt_filename = path.join(gt_folder, image_filenames[i])
            if path.exists(gt_filename):
                gt_label = imageio.imread(gt_filename)
            else:
                raise ValueError('not find image_filenames '.format(image_filenames[i]))

        cup_dices[i], disc_dices[i], ae_cdrs[i] = evaluate_binary_segmentation(segmentation, gt_label)

    return image_filenames, cup_dices, disc_dices, ae_cdrs


def evaluate_binary_segmentation(segmentation, gt_label):
    cup_dice = dice_coefficient(segmentation == 0, gt_label == 0)
    disc_dice = dice_coefficient(segmentation < 255, gt_label < 255)
    cdr = absolute_error(vertical_cup_to_disc_ratio(segmentation), vertical_cup_to_disc_ratio(gt_label))

    return cup_dice, disc_dice, cdr


def absolute_error(predicted, reference):
    return abs(predicted - reference)


def vertical_cup_to_disc_ratio(segmentation):
    cup_diameter = vertical_diameter(segmentation == 0)
    disc_diameter = vertical_diameter(segmentation < 255)

    return cup_diameter / (disc_diameter + EPS)


def vertical_diameter(binary_segmentation):
    binary_segmentation = np.asarray(binary_segmentation, dtype=np.bool)

    vertical_axis_diameter = np.sum(binary_segmentation, axis=0)

    diameter = np.max(vertical_axis_diameter)

    return float(diameter)


def dice_coefficient(binary_segmentation, binary_gt_label):
    binary_segmentation = np.asarray(binary_segmentation, dtype=np.bool)
    binary_gt_label = np.asarray(binary_gt_label, dtype=np.bool)

    intersection = np.logical_and(binary_segmentation, binary_gt_label)

    segmentation_pixels = float(np.sum(binary_segmentation.flatten()))
    gt_label_pixels = float(np.sum(binary_gt_label.flatten()))
    intersection = float(np.sum(intersection.flatten()))

    dice_value = 2 * intersection / (segmentation_pixels + gt_label_pixels)

    return dice_value


def get_mean_values_from_table(cup_dices, disc_dices, ae_cdrs):
    mean_cup_dice = np.mean(cup_dices)
    mean_disc_dice = np.mean(disc_dices)
    mae_cdr = np.mean(ae_cdrs)

    return mean_cup_dice, mean_disc_dice, mae_cdr


def evaluate_segmentation_results(segmentation_folder, gt_folder, output_path=None, export_table=False,
                                  is_training=False):
    image_filenames = get_filenames(segmentation_folder, 'bmp')
    if len(image_filenames) == 0:
        raise ValueError()

    _, cup_dices, disc_dices, ae_cdrs = generate_table_of_results(image_filenames, segmentation_folder, gt_folder,
                                                                  is_training)

    mean_cup_dice, mean_disc_dice, mae_cdr = get_mean_values_from_table(cup_dices, disc_dices, ae_cdrs)
    print('Dice Optic Cup = {}\nDice Optic Disc = {}\nMAE CDR = {}'.format(str(mean_cup_dice), str(mean_disc_dice),
                                                                           str(mae_cdr)))

    return mean_cup_dice, mean_disc_dice, mae_cdr
